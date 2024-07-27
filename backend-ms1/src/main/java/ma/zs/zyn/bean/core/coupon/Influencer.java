package ma.zs.zyn.bean.core.coupon;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zs.zyn.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import ma.zs.zyn.zynerator.security.bean.User;

@Entity
@Table(name = "influencer")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="influencer_seq",sequenceName="influencer_seq",allocationSize=1, initialValue = 1)
public class Influencer  extends User    {


    public Influencer(String username) {
        super(username);
    }


    @Column(length = 500)
    private String description;













    public Influencer(){
        super();
    }

    public Influencer(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="influencer_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Influencer influencer = (Influencer) o;
        return id != null && id.equals(influencer.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

