package com.example.GenerativeAI0301.service;

import com.stripe.Stripe;
import com.stripe.model.Charge;
import com.stripe.exception.*;
import com.stripe.model.Token;
import com.stripe.param.ChargeCreateParams;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {
    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge chargeCreditCard(String token, double amount) throws AuthenticationException, InvalidRequestException,
            APIConnectionException, CardException, APIException {

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", (int) (amount * 100));
        chargeParams.put("currency", "USD");
        chargeParams.put("source", token);

        Charge charge = Charge.create(chargeParams);
        return charge;
    }
}
