package lt.biblioteka.biblioteka.mybatis.model;

public class Library {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LIBRARY.ID
     *
     * @mbg.generated Fri Mar 21 16:59:41 EET 2025
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column PUBLIC.LIBRARY.NAME
     *
     * @mbg.generated Fri Mar 21 16:59:41 EET 2025
     */
    private String name;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LIBRARY.ID
     *
     * @return the value of PUBLIC.LIBRARY.ID
     *
     * @mbg.generated Fri Mar 21 16:59:41 EET 2025
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LIBRARY.ID
     *
     * @param id the value for PUBLIC.LIBRARY.ID
     *
     * @mbg.generated Fri Mar 21 16:59:41 EET 2025
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column PUBLIC.LIBRARY.NAME
     *
     * @return the value of PUBLIC.LIBRARY.NAME
     *
     * @mbg.generated Fri Mar 21 16:59:41 EET 2025
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column PUBLIC.LIBRARY.NAME
     *
     * @param name the value for PUBLIC.LIBRARY.NAME
     *
     * @mbg.generated Fri Mar 21 16:59:41 EET 2025
     */
    public void setName(String name) {
        this.name = name;
    }
}