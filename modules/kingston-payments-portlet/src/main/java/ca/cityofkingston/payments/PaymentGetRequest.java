package ca.cityofkingston.payments;

public class PaymentGetRequest extends PaymentRequest {

	PaymentGetRequest(RequestContext requestContext) {
		super(requestContext);
	}

	@Override
	ClientResponse doSend() {
		return getServiceClient().get();
	}

}