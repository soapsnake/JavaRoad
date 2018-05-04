include "Model.thrift"

namespace java com.vico.license.service


service HelloWorldService {

  Model.com.ld.Person sayHello(1:required string name);

}