package com.epam;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Subscription {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @NotNull
    private LocalDate startDate;

    public long getUserId(){
        return user.getId();
    }
}
