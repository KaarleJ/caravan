package org.caravan.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rides")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ride {
  @Id
  @SequenceGenerator(name = "rideSeq", sequenceName = "ride_id_seq", allocationSize = 1, initialValue = 1)
  private String id;

  @ManyToOne
  @JoinColumn(name = "trip_id")
  private Trip trip;

  @ManyToOne
  @JoinColumn(name = "user_id")
  private User driver;

  @ManyToMany
  @JoinTable(name = "ride_passengers", joinColumns = @JoinColumn(name = "ride_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  private List<User> passengers;

  private int seats;
}
