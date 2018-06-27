package com.example.android.tourguide;

import android.os.Parcel;
import android.os.Parcelable;

public class TourEntry implements Parcelable {
    // method used by Parcelable creation
    public static final Creator<TourEntry> CREATOR
            = new Creator<TourEntry>() {
        public TourEntry createFromParcel(Parcel in) {
            return new TourEntry(in);
        }

        public TourEntry[] newArray(int size) {
            return new TourEntry[size];
        }
    };

    private final String title;
    private final String details;
    private final double stars;
    private final int reviewCount;
    private final int imageResourceID;
    private final double latitude;
    private final double longitude;
    private final boolean mappable;

    /**
     * {@link TourEntry}
     *
     * @param title           string containing the title of the entry
     * @param details         string containing the details of the entry
     * @param latitude        double containing the latitude of the location
     * @param longitude       double containing the longitude of the location
     * @param imageResourceID int containing the imageResourceID for the entry
     * @param stars           double for stars rated
     * @param reviewCount     int number of reviews
     */
    TourEntry(String title, String details, Double stars, int reviewCount, double latitude, double longitude, int imageResourceID) {
        this.title = title;
        this.details = details;
        this.stars = stars;
        this.reviewCount = reviewCount;
        this.latitude = latitude;
        this.longitude = longitude;
        this.mappable = true;
        this.imageResourceID = imageResourceID;
    }

    /**
     * @param in Parcel to read objects from
     */
    private TourEntry(Parcel in) {
        title = in.readString();
        details = in.readString();
        latitude = in.readDouble();
        longitude = in.readDouble();
        mappable = in.readByte() != 0;
        stars = in.readDouble();
        reviewCount = in.readInt();
        imageResourceID = in.readInt();
    }

    // Getter(s):
    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public Double getStars() {
        return stars;
    }

    public int getReviewCount() {
        return reviewCount;
    }

    public int getImageResourceID() {
        return imageResourceID;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    // returns true if an imageResourceID was specified
    public boolean hasImage() {
        return imageResourceID != -1;
    }

    // required method for Parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * @param dest  Parcel to write objects to
     * @param flags int Additional flags about how the object should be written. May be 0 or PARCELABLE_WRITE_RETURN_VALUE.
     */
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(details);
        dest.writeDouble(latitude);
        dest.writeDouble(longitude);
        dest.writeByte((byte) (mappable ? 1 : 0));
        dest.writeDouble(stars);
        dest.writeInt(reviewCount);
        dest.writeInt(imageResourceID);
    }
}
