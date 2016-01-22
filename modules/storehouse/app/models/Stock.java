package models;

public class Stock extends AbstractModel {

    private Long id;
    private String name;
    private long userId;
//    private User user;
//    private Set<StockTypeQuantity> stq;
	private String fullStockName;
	private boolean requireConfirmation;

	public Stock() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getFullStockName() {
		return fullStockName;
	}

	public void setFullStockName(String fullStockName) {
		this.fullStockName = fullStockName;
	}

	public boolean isRequireConfirmation() {
		return requireConfirmation;
	}

	public void setRequireConfirmation(boolean requireConfirmation) {
		this.requireConfirmation = requireConfirmation;
	}

}
