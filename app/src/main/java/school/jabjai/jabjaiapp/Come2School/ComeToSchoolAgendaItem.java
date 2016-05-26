package school.jabjai.jabjaiapp.Come2School;

/**
 * Created by Administrator on 26/5/2559.
 */
public class ComeToSchoolAgendaItem {
    public String date;
    public String comeTime;
    public String comeStatus;
    public String leaveTime;
    public String leaveStatus;
    public String remark;

    public ComeToSchoolAgendaItem(String date, String comeTime, String comeStatus, String leaveTime, String leaveStatus, String remark) {
        this.date = date;
        this.comeTime = comeTime;
        this.comeStatus = comeStatus;
        this.leaveTime = leaveTime;
        this.leaveStatus = leaveStatus;
        this.remark = remark;
    }
}
