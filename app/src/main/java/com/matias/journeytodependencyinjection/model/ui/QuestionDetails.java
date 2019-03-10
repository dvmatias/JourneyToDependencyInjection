package com.matias.journeytodependencyinjection.model.ui;

import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.google.gson.annotations.SerializedName;

public class QuestionDetails {

    @SerializedName("title")
    private final String title;

    @SerializedName("question_id")
    private final String id;

    @SerializedName("body")
    private final String body;

    @SerializedName("owner")
    private final Owner owner;

    /**
     * Constructor.
     */
    public QuestionDetails(String title, String id, String body, Owner owner) {
        this.title = title;
        this.id = id;
        this.body = body;
        this.owner = owner;
    }

    public Spanned getHtmlBody() {
        if (TextUtils.isEmpty(getBody())) {
            return Html.fromHtml("");
        }
        return Html.fromHtml(getBody());
    }

    public String getTitle() {
        return title;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public Owner getOwner() {
        return owner;
    }

    public class Owner {

        @SerializedName("reputation")
        private final int reputation;

        @SerializedName("user_id")
        private final long id;

        @SerializedName("title")
        private final String title;

        @SerializedName("user_type")
        private final String type;

        @SerializedName("profile_image")
        private final String imageUrl;

        @SerializedName("display_name")
        private final String name;

        @SerializedName("link")
        private final String link;

        /**
         * Constructor.
         */
        public Owner(int reputation, long id, String title, String type, String imageUrl,
                     String name, String link) {
            this.reputation = reputation;
            this.id = id;
            this.title = title;
            this.type = type;
            this.imageUrl = imageUrl;
            this.name = name;
            this.link = link;
        }

        public int getReputation() {
            return reputation;
        }

        public long getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getType() {
            return type;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public String getName() {
            return name;
        }

        public String getLink() {
            return link;
        }
    }
}
