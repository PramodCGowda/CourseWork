import java.io.IOException;
import java.io.*;


/* 
	Review class contains class variables username,productname,reviewText,reviewdate,reviewRating

	Review class has a constructor with Arguments username,productname,reviewText,reviewdate,reviewRating
	  
	Review class contains getters and setters for username,productname,reviewText,reviewdate,reviewRating
*/

public class Review implements Serializable{
	private String productName;
	private String userName;
	private String productType;
	private String productprice;
	private String storeId;
	private String StoreZip;
	private String StoreCity;
	private String StoreState;
	private String ProductOnSale;
	private String ManufacturerName;
	private String manufacturerRebate;
	private String Userage;
	private String userId;
	private String UserGender;
	private String UserOccupation;
	private String reviewRating;
	private String reviewdate;
	private String reviewText;

	public Review(String productName, String storeZip, String reviewRating, String reviewText) {
		this.productName = productName;
		this.StoreZip = storeZip;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
	}

	public Review(String productName, String userName, String productType, String manufacturerName, String reviewRating, String reviewdate,
				  String reviewText, String storeZip, String productprice, String storeCity) {
		this.productName = productName;
		this.userName = userName;
		this.productType = productType;
		this.productprice = productprice;
		this.StoreZip = storeZip;
		this.StoreCity = storeCity;
		this.ManufacturerName = manufacturerName;
		this.reviewRating = reviewRating;
		this.reviewdate = reviewdate;
		this.reviewText = reviewText;
	}
	public Review(String userName, String productName, String productType, String productprice, String storeId, String storeZip, String storeCity,
				  String storeState, String productOnSale, String manufacturerName, String manufacturerRebate, String userId, String userage,
				  String userGender, String userOccupation, String reviewRating, String reviewdate, String reviewText) {
		this.productName = productName;
		this.userName = userName;
		this.productType = productType;
		this.productprice = productprice;
		this.storeId = storeId;
		this.StoreZip = storeZip;
		this.StoreCity = storeCity;
		this.StoreState = storeState;
		this.ProductOnSale = productOnSale;
		this.ManufacturerName = manufacturerName;
		this.manufacturerRebate = manufacturerRebate;
		this.userId = userId;
		this.Userage = userage;
		this.UserGender = userGender;
		this.UserOccupation = userOccupation;
		this.reviewRating = reviewRating;
		this.reviewdate = reviewdate;
		this.reviewText = reviewText;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public String getProductprice() {
		return productprice;
	}

	public void setProductprice(String productprice) {
		this.productprice = productprice;
	}

	public String getStoreId() {
		return storeId;
	}

	public void setStoreId(String storeId) {
		this.storeId = storeId;
	}

	public String getStoreZip() {
		return StoreZip;
	}

	public void setStoreZip(String storeZip) {
		StoreZip = storeZip;
	}

	public String getStoreCity() {
		return StoreCity;
	}

	public void setStoreCity(String storeCity) {
		StoreCity = storeCity;
	}

	public String getStoreState() {
		return StoreState;
	}

	public void setStoreState(String storeState) {
		StoreState = storeState;
	}

	public String getProductOnSale() {
		return ProductOnSale;
	}

	public void setProductOnSale(String productOnSale) {
		ProductOnSale = productOnSale;
	}

	public String getManufacturerName() {
		return ManufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		ManufacturerName = manufacturerName;
	}

	public String getUserage() {
		return Userage;
	}

	public void setUserage(String userage) {
		Userage = userage;
	}

	public String getUserGender() {
		return UserGender;
	}

	public void setUserGender(String userGender) {
		UserGender = userGender;
	}

	public String getUserOccupation() {
		return UserOccupation;
	}

	public void setUserOccupation(String userOccupation) {
		UserOccupation = userOccupation;
	}

	public String getReviewrating() {
		return reviewRating;
	}

	public void setReviewrating(String reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewdate() {
		return reviewdate;
	}

	public void setReviewdate(String reviewdate) {
		this.reviewdate = reviewdate;
	}

	public String getReviewtext() {
		return reviewText;
	}

	public void setReviewtext(String reviewText) {
		this.reviewText = reviewText;
	}

	public String getManufacturerRebate() {
		return manufacturerRebate;
	}

	public void setManufacturerRebate(String manufacturerRebate) {
		this.manufacturerRebate = manufacturerRebate;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
