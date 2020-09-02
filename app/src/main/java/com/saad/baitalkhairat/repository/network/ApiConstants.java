package com.saad.baitalkhairat.repository.network;

public class ApiConstants {
    public static final String BASE_URL = "https://test-1.baytalkhayrat.net/api/v1/";
    public static final String GET_DATA = "user/1/categories";
    public static final String PLATFORM = "android";
    public static final int PASSPORT_CLIENT_ID = 1;
    public static final String PASSPORT_CLIENT_SECRET = "7i5HrPXnIZo5pl8MXxIXDmmrwLDRyHov6yFmuONc";

    public class apiAuthService {
        public static final String VERIFY_PHONE = "member/password/mobile/otp/verify";
        public static final String VERIFY_CODE = "member/register/otp/verify";
        public static final String RESEND_CODE = "member/register/otp/resend";
        public static final String RESEND_CODE_TP_FORGET_PASSWORD = "member/password/mobile/otp/resend";
        public static final String REGISTER_USER = "member/register";
        public static final String LOGIN_USER = "member/login";
        public static final String LOGIN_SOCIAL = "auth/login/socialMedia";
        public static final String FORGET_PASSWORD = "member/password/mobile";
        public static final String CREATE_PASSWORD = "member/password/reset/mobile";
        public static final String UPDATE_FIREBASE_TOKEN = "user/update/device-token";
        public static final String LOGOUT = "member/logout";
        public static final String UPDATE_PASSWORD = "user/update/password";
        public static final String UPDATE_PROFILE = "member/my-profile";
        public static final String REFRESH_TOKEN = "member/refresh-token";
        public static final String MY_PROFILE = "member/my-profile";
        public static final String ADD_ATTACHMENT = "member/my-profile/attachments";
        public static final String DELETE_ATTACHMENT = "member/my-profile/attachments/{id}/delete";
    }

    public class apiHomeService {
        public static final String HOME_CATEGORIES = "app/home";
        public static final String CATEGORY_SERVICES = "services/category/{category_id}";
    }

    public class apiWalletService {
        public static final String WALLET = "member/wallets";
        public static final String TRANSACTIONS = "member/wallets/statements";
    }

    public class apiAddressService {
        public static final String GET_ADDRESS = "user/customer/addresses";
        public static final String DELETE_ADDRESS = "user/customer/addresses/destroy/{addressId}";
        public static final String CREATE_ADDRESS = "user/customer/addresses/create";
    }

    public class apiOrderService {
        public static final String UPLOAD_ORDER_IMAGES = "user/customer/orders/up-image";
        public static final String CANCELLATION_FEE = "user/customer/orders/wrongOrderCharge/{orderId}";
        public static final String CANCEL_ORDER = "user/customer/orders/cancel/{orderId}";
        public static final String SUBMIT_ORDER = "user/customer/orders/submit";
        public static final String CALCULATE_PRICE = "user/customer/orders/calculatePrice/{serviceId}";
        public static final String CALCULATE_OFFER_BUT_LIMITED = "user/customer/orders/calcPriceOfferButLimited/{serviceId}";
        public static final String ACTIVE_ORDER = "user/customer/orders/active";
        public static final String HISTORY_ORDER = "user/customer/orders/previous";
        public static final String UNRATED_ORDER = "user/customer/orders/getOrdersNotRate";
        public static final String ORDER_DETAILS = "user/customer/orders/{orderId}/show";
        public static final String RATE_ORDERS = "user/customer/orders/orderRate/{orderId}";
        public static final String OFFERS = "user/customer/orders/{orderId}/offers";
        public static final String HIRE_WORKER = "user/customer/orders/hireWorker/{orderId}/{offerId}";

    }

    public class apiAppService {
        public static final String COUNTRY_CODE = "lists/countries-code";
        public static final String COUNTRY_NAME = "lists/countries-name";
        public static final String GENDERS = "lists/gender";
        public static final String MARITALS = "lists/marital-status";
        public static final String SLIDERS = "banners";
        public static final String QUESTION = "faqs";
        public static final String TERMS_OF_USE = "terms-of-uses";
        public static final String ABOUT_US = "system-information/about-us";
        public static final String ABOUT_US_SECTIONS = "about-us-sections";
        public static final String FUNDING_RESOURCE = "funding-sources";
        public static final String BAIT_RESOURCE = "system-information/funding-source";
        public static final String CONTACT_US = "system-information/contact-us";
        public static final String APP_BANK_INFO = "system-information/bank";
        public static final String CASE_TYPE = "lists/need-request/type";
        public static final String DEGREE = "lists/need-request/degree";
        public static final String HUMANITY_VALUES = "our-humanity-values";
        public static final String NEWS = "news";
        public static final String PRIVACY_POLICY = "privacy-polices";
    }

    public class apiDonorsService {
        public static final String NEEDS_CATEGORIES_WITH_NO_COLOR = "lists/need-request/type";
        public static final String NEEDS_CATEGORIES = "need-request-types";
        public static final String CATEGORY_CASES = "need-requests";
        public static final String ADD_TO_CART = "need-requests/{id}/add-to-cart";
        public static final String GET_CART = "carts";
        public static final String DELETE_CART = "carts/{id}/destroy";
        public static final String CHECKOUT = "carts/checkout";
        public static final String HISTORY_NEEDS = "member/donations/previous";
        public static final String CURRENT_NEEDS = "member/donations/current";
    }

    public class apiNeedsService {
        public static final String ADD_NEED = "member/need-requests";
        public static final String ADD_NEED_DOCS = "member/need-requests/attachments";
        public static final String CURRENT_NEEDS = "member/need-requests/current";
        public static final String HISTORY_NEEDS = "member/need-requests/previous";
    }

}
