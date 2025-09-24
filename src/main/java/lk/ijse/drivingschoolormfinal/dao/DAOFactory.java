package lk.ijse.drivingschoolormfinal.dao;


import lk.ijse.drivingschoolormfinal.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory instance;
    public static DAOFactory getInstance(){
        if(instance==null){
            instance=new DAOFactory();

        }
        return instance;
    }
    private DAOFactory(){

    }
    public enum DAOtypes{
        STUDENT,INSTRUCTOR,COURSE,LESSON,PAYMENT,USER
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case STUDENT:
              return new StudentDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
                case COURSE:
                    return new CourseDAOImpl();
                    case LESSON:
                        return new LessonDAOImpl();
                        case PAYMENT:
                            return new PaymentDAOImpl();
                            case USER:
                                return new UserDAOImpl();
              default:
                  return null;
        }
    }
}
