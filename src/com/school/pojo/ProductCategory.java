package com.school.pojo;

public class ProductCategory {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_category.id
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_category.name
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_category.parentId
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    private Integer parentid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_category.type
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column product_category.iconClass
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    private String iconclass;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_category.id
     *
     * @return the value of product_category.id
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_category.id
     *
     * @param id the value for product_category.id
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_category.name
     *
     * @return the value of product_category.name
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_category.name
     *
     * @param name the value for product_category.name
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_category.parentId
     *
     * @return the value of product_category.parentId
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public Integer getParentid() {
        return parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_category.parentId
     *
     * @param parentid the value for product_category.parentId
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_category.type
     *
     * @return the value of product_category.type
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_category.type
     *
     * @param type the value for product_category.type
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column product_category.iconClass
     *
     * @return the value of product_category.iconClass
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public String getIconclass() {
        return iconclass;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column product_category.iconClass
     *
     * @param iconclass the value for product_category.iconClass
     *
     * @mbggenerated Sun Jul 07 17:44:58 CST 2019
     */
    public void setIconclass(String iconclass) {
        this.iconclass = iconclass == null ? null : iconclass.trim();
    }
}