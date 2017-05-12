package rjava;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Regression {
    private static Logger logger = Logger.getLogger(Regression.class.getName());
    public static void main (String args[]){
        logger.log(Level.INFO,"Starting R connection");
        try {
            RConnection rconn = new RConnection();
            logger.info("Successfully connected to R");
            String cmd = "capture.output(summary(lm(mpg~wt,data=mtcars)))";
            logger.info("evaluating:"+cmd);
            REXP rexpr = rconn.eval(cmd);
            String strings[] = rexpr.asStrings();
            for(String s:strings){
                logger.info("**Outline**: "+s);
            }
        } catch (RserveException e) {
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }

    }
}
