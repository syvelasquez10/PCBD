package co.edu.uniandes.andar.models;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class BookingEntity implements Serializable{
     private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(generator = "Booking")
    private Long id;
    
    private String name;
    
    private String email;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date timeDay;
  
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getTimeDay() {
        return timeDay;
    }

    public void setTimeDay(Date timeDay) {
        this.timeDay = timeDay;
    }
}
