package np.com.prahladpanthi.seminaronebackend.util;

public interface APIConstants {

    String BASE_API = "/api/seminarone";

    /****************** COMMON ****************/

    String FIND_ALL = "/findAll";
    String FIND_BY_ID = "/find/{id}";
    String SAVE = "/save";
    String UPDATE = "/update";
    String UPDATE_BY_ID = "/update/{id}";
    String DELETE = "/delete";
    String DELETE_BY_ID = "/delete/{id}";

    /****************** APIs FOR SEMINAR CONTROLLER ****************/

    String SEMINAR = "/seminar";

    String ADD_SEMINAR = "/add";

    /****************** BRANCH ******************/

    String BRANCH = "/branch";
    String FIND_BY_BRANCH_NUMBER =  "/{branchNumber}";

    /****************** LOCATION ****************/
    String LOCATION = "/location";


    /**************** PRODUCT *****************/
    String PRODUCT = BASE_API + "/product";

    /**************** TOPIC *******************/
    String TOPIC = BASE_API + "/topic";

    /***************** STAFF *****************/
    String STAFF = BASE_API + "/staff";





















}
