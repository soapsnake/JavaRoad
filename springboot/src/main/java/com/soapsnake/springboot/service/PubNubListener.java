package com.soapsnake.springboot.service;


import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.pubnub.api.PubNub;
import com.pubnub.api.callbacks.PNCallback;
import com.pubnub.api.callbacks.SubscribeCallback;
import com.pubnub.api.enums.PNStatusCategory;
import com.pubnub.api.models.consumer.PNPublishResult;
import com.pubnub.api.models.consumer.PNStatus;
import com.pubnub.api.models.consumer.objects_api.space.PNSpace;
import com.pubnub.api.models.consumer.objects_api.user.PNUser;
import com.pubnub.api.models.consumer.pubsub.PNMessageResult;
import com.pubnub.api.models.consumer.pubsub.PNPresenceEventResult;
import com.pubnub.api.models.consumer.pubsub.PNSignalResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNMembershipResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNSpaceResult;
import com.pubnub.api.models.consumer.pubsub.objects.PNUserResult;

@Service
public class PubNubListener {

    @Resource
    private PubNub pubNub;

    @Value("${pubChannel : awesomeChannel}")
    private String publishChannel;

    @Value("${listenChannel : pubnub-wikipedia}")
    private String listenChannel;

    public void publishMsg() {
        // create message payload using Gson
        JsonObject messageJsonObject = new JsonObject();
        messageJsonObject.addProperty("msg", "hello");
        System.out.println("Message to send: " + messageJsonObject.toString());

        pubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getCategory() == PNStatusCategory.PNUnexpectedDisconnectCategory) {
                    // This event happens when radio / connectivity is lost
                    System.out.println("pubnub connectivity is lost");
                } else if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
                    // Connect event. You can do stuff like publish, and know you'll get it.
                    // Or just use the connected event to confirm you are subscribed for
                    // UI / internal notifications, etc

                    if (status.getCategory() == PNStatusCategory.PNConnectedCategory) {
                        pubnub.publish().channel(publishChannel).message(messageJsonObject).async(new PNCallback<PNPublishResult>() {
                            @Override
                            public void onResponse(PNPublishResult result, PNStatus status) {
                                // Check whether request successfully completed or not.
                                if (!status.isError()) {
                                    System.out.println("pubnub => reesult: " + JSON.toJSONString(result));
                                    // Message successfully published to specified channel.
                                } else {  // Request processing failed.
                                    // Handle message publish error. Check 'category' property to find out possible issue
                                    // because of which request did fail.
                                    //
                                    // Request can be resent using: [status retry];
                                }
                            }
                        });
                    }
                } else if (status.getCategory() == PNStatusCategory.PNReconnectedCategory) {
                    System.out.println("radio / connectivity is lost, then regained");

                    // Happens as part of our regular operation. This event happens when
                    // radio / connectivity is lost, then regained.
                } else if (status.getCategory() == PNStatusCategory.PNDecryptionErrorCategory) {
                    System.out.println("Handle messsage decryption error");
                    // Handle messsage decryption error. Probably client configured to
                    // encrypt messages and on live data feed it received plain text.
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                // Handle new message stored in message.message
                if (message.getChannel() != null) {
                    // Message has been received on channel group stored in
                    // message.getChannel()
                    System.out.println("Received message content: " + message.getChannel());
                } else {
                    // Message has been received on channel stored in
                    // message.getSubscription()
                    System.out.println("Received message content: " + message.getSubscription());
                }

                JsonElement receivedMessageObject = message.getMessage();
                System.out.println("Received message content: " + receivedMessageObject.toString());
                // extract desired parts of the payload, using Gson
                String msg = message.getMessage().getAsJsonObject().get("msg").getAsString();
                System.out.println("msg content: " + msg);

            /*
                log the following items with your favorite logger
                    - message.getMessage()
                    - message.getSubscription()
                    - message.getTimetoken()
            */
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }

            @Override
            public void signal(PubNub pubnub, PNSignalResult pnSignalResult) {

            }

            @Override
            public void user(PubNub pubnub, PNUserResult pnUserResult) {

            }

            @Override
            public void space(PubNub pubnub, PNSpaceResult pnSpaceResult) {

            }

            @Override
            public void membership(PubNub pubnub, PNMembershipResult pnMembershipResult) {

            }
        });

        pubNub.subscribe().channels(Arrays.asList(publishChannel)).execute();
    }

    @PostConstruct
    public void listen() {
        pubNub.addListener(new SubscribeCallback() {
            @Override
            public void status(PubNub pubnub, PNStatus status) {
                if (status.getOperation() != null) {
                    switch (status.getOperation()) {
                        // let's combine unsubscribe and subscribe handling for ease of use
                        case PNSubscribeOperation:
                        case PNUnsubscribeOperation:
                            // note: subscribe statuses never have traditional
                            // errors, they just have categories to represent the
                            // different issues or successes that occur as part of subscribe
                            switch (status.getCategory()) {
                                case PNConnectedCategory:
                                    // this is expected for a subscribe, this means there is no error or issue whatsoever
                                case PNReconnectedCategory:
                                    // this usually occurs if subscribe temporarily fails but reconnects. This means
                                    // there was an error but there is no longer any issue
                                case PNDisconnectedCategory:
                                    // this is the expected category for an unsubscribe. This means there
                                    // was no error in unsubscribing from everything
                                case PNUnexpectedDisconnectCategory:
                                    // this is usually an issue with the internet connection, this is an error, handle appropriately
                                case PNAccessDeniedCategory:
                                    // this means that PAM does allow this client to subscribe to this
                                    // channel and channel group configuration. This is another explicit error
                                default:
                                    // More errors can be directly specified by creating explicit cases for other
                                    // error categories of `PNStatusCategory` such as `PNTimeoutCategory` or `PNMalformedFilterExpressionCategory` or `PNDecryptionErrorCategory`
                            }

                        case PNHeartbeatOperation:
                            // heartbeat operations can in fact have errors, so it is important to check first for an error.
                            // For more information on how to configure heartbeat notifications through the status
                            // PNObjectEventListener callback, consult <link to the PNCONFIGURATION heartbeart config>
                            if (status.isError()) {
                                // There was an error with the heartbeat operation, handle here
                                System.out.println(status);
                            } else {
                                // heartbeat operation was successful
                            }
                        default: {
                            // Encountered unknown status type
                        }
                    }
                } else {
                    // After a reconnection see status.getCategory()
                }
            }

            @Override
            public void message(PubNub pubnub, PNMessageResult message) {
                String messagePublisher = message.getPublisher();
                System.out.println("Message publisher: " + messagePublisher);
                System.out.println("Message Payload: " + message.getMessage());
                System.out.println("Message Subscription: " + message.getSubscription());
                System.out.println("Message Channel: " + message.getChannel());
                System.out.println("Message timetoken: " + message.getTimetoken());
            }

            @Override
            public void presence(PubNub pubnub, PNPresenceEventResult presence) {

            }

            @Override
            public void signal(PubNub pubnub, PNSignalResult pnSignalResult) {
                System.out.println("Signal publisher: " + pnSignalResult.getPublisher());
                System.out.println("Signal payload: " + pnSignalResult.getMessage());
                System.out.println("Signal subscription: " + pnSignalResult.getSubscription());
                System.out.println("Signal channel: " + pnSignalResult.getChannel());
                System.out.println("Signal timetoken: " + pnSignalResult.getTimetoken());
            }

            @Override
            public void user(PubNub pubnub, PNUserResult pnUserResult) {
                // for Objects, this will trigger when:
                // . user updated
                // . user deleted
                PNUser pnUser = pnUserResult.getUser(); // the user for which the event applies to
                pnUserResult.getEvent(); // the event name
            }

            @Override
            public void space(PubNub pubnub, PNSpaceResult pnSpaceResult) {
                // for Objects, this will trigger when:
                // . space updated
                // . space deleted
                PNSpace pnSpace = pnSpaceResult.getSpace(); // the space for which the event applies to
                pnSpaceResult.getEvent(); // the event name
            }

            @Override
            public void membership(PubNub pubnub, PNMembershipResult pnMembershipResult) {
                // for Objects, this will trigger when:
                // . user added to a space
                // . user removed from a space
                // . membership updated on a space
                JsonElement data = pnMembershipResult.getData(); // membership data for which the event applies to
                pnMembershipResult.getEvent(); // the event name
            }
        });

        pubNub.subscribe().channels(Arrays.asList(listenChannel)).execute();
    }


}
