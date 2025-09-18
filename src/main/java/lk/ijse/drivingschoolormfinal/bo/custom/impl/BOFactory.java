package lk.ijse.drivingschoolormfinal.bo.custom.impl;

import lk.ijse.drivingschoolormfinal.bo.SuperBO;


public class BOFactory {
        private static lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory instance;
        public static lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory getInstance(){
            if(instance==null){
                instance=new lk.ijse.drivingschoolormfinal.bo.custom.impl.BOFactory();

            }
            return instance;
        }
        private BOFactory(){

        }
        public enum BOtypes{
            STUDENT,INSTRUCTOR,COURSE
        }
        public SuperBO getBO(BOtypes bo){
            switch(bo){
                case STUDENT:
                    return new StudentBOImpl();
                case INSTRUCTOR:
                    return new InstructorBOImpl();
                    case COURSE:
                        return new CourseBOImpl();
                default:
                    return null;
            }
        }
    }


