package ua.se.sample.config;

// todo: rename
public class ControllersApiPaths {

    private ControllersApiPaths(){

    }

    public static final String BASE_PATH = "/api/v1";

    public static final String CREATE = "/";
    public static final String GET_ITEMS = "/";
    public static final String GET_ITEM = "/{name}";
    public static final String UPDATE = "/{id}";
    public static final String DELETE = "/{id}";
    public static final String UPLOAD_IMAGE = "/{id}/image";
}
