package edu.lewisu.cs.phuonghongpvu.githubjobca;

import android.os.Parcel;
import android.os.Parcelable;

public class Job implements Parcelable {
    private String title;
    private String company;
    private String type;
    private String location;
    private String url;
    private String description;



    public Job(String title, String company, String type, String location, String url, String description) {
        this.title = title;
        this.company = company;
        this.type = type;
        this.location = location;
        this.url = url;
        this.description = description;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    protected Job(Parcel in) {
        title = in.readString();
        company = in.readString();
        type = in.readString();
        location = in.readString();
        url = in.readString();
        description = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(company);
        dest.writeString(type);
        dest.writeString(location);
        dest.writeString(url);
        dest.writeString(description);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Job> CREATOR = new Parcelable.Creator<Job>() {
        @Override
        public Job createFromParcel(Parcel in) {
            return new Job(in);
        }

        @Override
        public Job[] newArray(int size) {
            return new Job[size];
        }
    };
}