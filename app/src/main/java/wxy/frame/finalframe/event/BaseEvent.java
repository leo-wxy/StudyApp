package wxy.frame.finalframe.event;

/**
 * Created by wxy on 2016/11/18.
 */

public interface BaseEvent {

    void setObject(Object obj);

    Object getObject();


    class CommonEvent implements BaseEvent {
        private Object obj;

        public CommonEvent(Object obj) {
            this.obj = obj;
        }

        @Override
        public void setObject(Object obj) {
            this.obj = obj;
        }

        @Override
        public Object getObject() {
            return obj;
        }
    }

}
