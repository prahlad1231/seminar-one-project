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

    String SEMINAR = BASE_API + "/seminar";

    /****************** BRANCH ******************/

    String BRANCH = BASE_API + "/branch";
    String FIND_BY_BRANCH_NUMBER =  "/{branchNumber}";
}
