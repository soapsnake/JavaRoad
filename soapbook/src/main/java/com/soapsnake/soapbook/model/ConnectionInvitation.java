package com.soapsnake.soapbook.model;

import java.util.Date;

import com.soapsnake.soapbook.enums.ConnectionInvitationStatus;

/**
 *
 * Created on 2020-06-01
 */
public class ConnectionInvitation {
    private Member memberInvited;
    private ConnectionInvitationStatus status;
    private Date dateCreated;
    private Date dateUpdated;

    public Boolean acceptConnection() {
        return null;
    }

    public Boolean rejectConnection() {
        return null;
    }
}
