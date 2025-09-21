package lk.ijse.drivingschoolormfinal.dao;


import lk.ijse.drivingschoolormfinal.dao.custom.impl.CourseDAOImpl;
import lk.ijse.drivingschoolormfinal.dao.custom.impl.InstructorDAOImpl;
import lk.ijse.drivingschoolormfinal.dao.custom.impl.LessonDAOImpl;
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
        STUDENT,INSTRUCTOR,COURSE,LESSON
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
              default:
                  return null;
        }
    }
}
