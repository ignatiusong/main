package seedu.foodiebot.model.transaction;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;

import seedu.foodiebot.model.food.Food;
import seedu.foodiebot.model.rating.Rating;
import seedu.foodiebot.model.rating.Review;
import seedu.foodiebot.model.tag.Tag;

/** Represents a purchased food. This class extends the {@link Food} class with the added fields of
 * date purchased, rating as well as a review. */
public class PurchasedFood extends Food implements Comparable<PurchasedFood> {

    private final LocalDate dateAdded;
    private final LocalTime timeAdded;
    private Rating rating;
    private Review review;

    /**
     * Every field must be present and not null.
     */
    public PurchasedFood(String name,
                         int price,
                         String description,
                         String foodImageName,
                         int stallNo,
                         String canteen,
                         String stallName,
                         Set<Tag> tags,
                         LocalDate dateAdded,
                         LocalTime timeAdded,
                         Rating rating,
                         Review review) {
        super(name, price, description, foodImageName, stallNo, canteen, stallName, tags);
        this.dateAdded = dateAdded;
        this.timeAdded = timeAdded;
        this.rating = rating;
        this.review = review;
    }

    public PurchasedFood(Food food, LocalDate dateAdded, LocalTime timeAdded, Rating rating, Review review) {
        this(food.getName(), food.getPrice(), food.getDescription(), food.getName(), food.getStallNo(),
                food.getCanteen(), food.getStallName(), food.getTags(),
                dateAdded, timeAdded, rating, review);
    }

    public void setRating(Rating newRating) {
        this.rating = newRating;
    }

    public void setReview(Review newReview) {
        this.review = newReview;
    }

    public LocalDate getDateAdded() {
        return this.dateAdded;
    }

    public LocalTime getTimeAdded() {
        return this.timeAdded;
    }

    public Rating getRating() {
        return this.rating;
    }

    public Review getReview() {
        return this.review;
    }

    @Override
    public int compareTo(PurchasedFood otherFood) {
        int canteenCompare = getCanteen().compareTo(otherFood.getCanteen());
        if (canteenCompare == 0) {
            int stallCompare = getStallName().compareTo(otherFood.getStallName());
            if (stallCompare == 0) {
                return getName().compareTo(otherFood.getName());
            } else {
                return stallCompare;
            }
        } else {
            return canteenCompare;
        }
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof PurchasedFood)) {
            return false;
        }

        PurchasedFood otherFood = (PurchasedFood) other;
        return otherFood.getName().equals(getName())
                && otherFood.getReview() == getReview()
                && otherFood.getPrice() == (getPrice())
                && otherFood.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(getName(), getPrice(), getDescription(), getTags());
    }


}
