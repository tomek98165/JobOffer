package com.joboffers;

public interface SampleJonOffersResponse {

    default String bodyWithNoOffersJson(){
        return "[]";
    }

    default String bodyWithOneOfferJson(){
        return """
                [
                {
                "title": "Junior Java DEV",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer1"
                }
                ]
                """.trim();
    }

    default String bodyWithTwoOffersJson(){
        return """
                [
                {
                "title": "Junior Java DEV",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer1"
                },
                {
                "title": "DevOPS",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer2"
                }
                ]
                """.trim();
    }

    default String bodyWithFourOffersJson(){
        return """
                [
                {
                "title": "Junior Java DEV",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer1"
                },
                {
                "title": "DevOPS",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer2"
                },
                {
                "title": "Python Developer",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer3"
                },
                {
                "title": "Analityk systemowy",
                "company": "WoW",
                "salary": "5k - 8k",
                "offerUrl": "link.com/offer4"
                }
                ]
                """.trim();
    }
}
