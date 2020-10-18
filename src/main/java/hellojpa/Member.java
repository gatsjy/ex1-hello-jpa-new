package hellojpa;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Gatsjy on 2020-10-18
 * Blog : https://blog.naver.com/gkswndks123
 * Github : https://github.com/gatsjy
 */

@Entity
public class Member {

    @Id
    private Long id;
    private String name;

    protected Member(){
    }

    public Member(Long id, String name){
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

