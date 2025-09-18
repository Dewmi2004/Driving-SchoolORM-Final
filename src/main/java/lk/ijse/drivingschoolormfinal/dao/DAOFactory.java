package lk.ijse.drivingschoolormfinal.dao;


import lk.ijse.drivingschoolormfinal.dao.custom.impl.CourseDAOImpl;
import lk.ijse.drivingschoolormfinal.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingschoolormfinal.dao.custom.impl.StudentDAOImpl;

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
        STUDENT,INSTRUCTOR,COURSE
    }
    public SuperDao getDAO(DAOtypes dao){
        switch(dao){
            case STUDENT:
              return new StudentDAOImpl();
            case INSTRUCTOR:
                return new InstructorDAOImpl();
                case COURSE:
                    return new CourseDAOImpl();
              default:
                  return null;
        }
    }
}
