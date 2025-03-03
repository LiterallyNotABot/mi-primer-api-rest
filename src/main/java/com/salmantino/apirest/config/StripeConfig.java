package com.salmantino.apirest.config;

import com.stripe.Stripe;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StripeConfig {

    public StripeConfig() {
        Stripe.apiKey = "sk_test_51QuZCQPdMh0chZHFYp8nRrBRKEYncGoaNefqfKSqKprIdP6pKYa26SqW5g8zqivZwQKrBZdK4NsYL09VzOxrp7ND00u6cY2GL1";
    }
}
