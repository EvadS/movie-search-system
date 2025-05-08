package ua.se.sample.config;

// todo: rename
public class ControllersApiPaths {

    private ControllersApiPaths(){

    }

    public static final String BASE_PATH = "/api/v1";

    public static final String KEYWORD_PATH = "/keyword";
    public static final String COUNTRY_PATH = "/country";

    public static final String COMPANY_PATH = "/company";
    public static final String DEPARTMENT_PATH = "/department";
    public static final String GENDER_PATH = "/gender";
    public static final String GENRE_PATH = "/genre";
    public static final String LANGUAGE_PATH = "/language";
    public static final String PERSON_PATH = "/person";

    public static final String MOVIE_PATH = "/movie";


    public static final String CREATE = "/";
    public static final String GET_ITEMS = "/";
    public static final String GET_ITEM_BY_NAME = "/{name}";
    public static final String GET_ITEM_BY_ID = "/{id}";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";
    public static final String UPLOAD_IMAGE = "/{id}/image";


    public static final String PAGED = "/paged";


}
