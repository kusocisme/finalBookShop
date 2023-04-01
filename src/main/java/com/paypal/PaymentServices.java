package com.paypal;

import java.util.ArrayList;
import java.util.List;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

public class PaymentServices {
	private static final String CLIENT_ID = "AQ0CLC-BP2_-LjDMll1SmYMM1Q12W42BMSMIao9fxxrrbB6ogIMC2WnyQT5fmPhjQBqifPsOLoWoy-UK";
	private static final String CLIENT_SECRET = "EJ0p8q_9FYfaQ57ueYfZ6Ah8jO9tdcuJJ2Rc9spM--ua036QdWlxIwirzlCI_rhFQXMGrOwYrXf-GcgQ";
	private static final String MODE = "sandbox";
	
	public String authorizePayment(Checkout checkout, Payer payer) throws PayPalRESTException {
		
		getRedirectURLs();
		List<Transaction> listTransactions = getTransacitonInfo(checkout);
		Payment requestPayment = new Payment();
		requestPayment.setTransactions(listTransactions)
					  .setRedirectUrls(getRedirectURLs())
					  .setPayer(payer)
					  .setIntent("authorize");
		APIContext apiContent = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		Payment approvedPayment = requestPayment.create(apiContent);
		
		System.out.println(approvedPayment);
		return getApprovalLink(approvedPayment);
	}
	
	private String getApprovalLink(Payment approvedPayment) {
		List<Links> links = approvedPayment.getLinks();
		String approvalLink = null;
		for(Links link : links) {
			if (link.getRel().equalsIgnoreCase("approval_url")) {
				approvalLink = link.getHref();
			}
		}
		return approvalLink;
	}
	private List<Transaction> getTransacitonInfo(Checkout checkout) {
		Details details = new Details();
		details.setShipping(checkout.getShipping());
		details.setSubtotal(checkout.getSubTotal());
		details.setTax(checkout.getTax());
		
		Amount amount = new Amount();
		amount.setCurrency("USD");
		amount.setTotal(checkout.getTotal());
		amount.setDetails(details);
		
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction.setDescription(checkout.getProductName());
		
		ItemList itemList = new ItemList();
		List<Item> items = new ArrayList<Item>();
		Item item = new Item();
		item.setCurrency("USD")
		.setName(checkout.getProductName())
		.setPrice(checkout.getSubTotal())
		.setTax(checkout.getTax())
		.setQuantity("1");
		
		items.add(item);
		itemList.setItems(items);
		transaction.setItemList(itemList);
		List<Transaction> listTransactions = new ArrayList<Transaction>();
		listTransactions.add(transaction);
		
		return listTransactions;
	}
	private RedirectUrls getRedirectURLs() {
		RedirectUrls redirect = new RedirectUrls();
		
//		redirect.setCancelUrl("https://ebooks-shop.herokuapp.com/cancel");
//		redirect.setReturnUrl("https://ebooks-shop.herokuapp.com/review_payment");
		redirect.setCancelUrl("http://localhost:8080/bookshop/cancel");
		redirect.setReturnUrl("http://localhost:8080/bookshop/review_payment");
		return redirect;
	}
	public Payer getPayerInformation(String fname, String lname, String email) {
		
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");
		PayerInfo payerInfo = new PayerInfo();
		payerInfo.setFirstName(fname).setLastName(lname).setEmail(email);
		
		payer.setPayerInfo(payerInfo);
		
		return payer;
	}
	public Payment getPaymentDetail(String pId) throws PayPalRESTException{
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return Payment.get(apiContext, pId);
	}
	public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		
		Payment payment = new Payment().setId(paymentId);
		APIContext apiContext = new APIContext(CLIENT_ID, CLIENT_SECRET, MODE);
		return payment.execute(apiContext, paymentExecution);
		
		
	}
}
