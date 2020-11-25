package ca.cityofkingston.payments;


public class PaymentPostRequest extends PaymentRequest {

	public PaymentPostRequest(RequestContext requestContext) {

		super(requestContext);
	}
	
	@Override
	ClientResponse doSend() {
		return getServiceClient().post();
	}
	
}
