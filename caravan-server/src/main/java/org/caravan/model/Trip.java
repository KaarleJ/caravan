package org.caravan.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.caravan.model.enums.TripStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
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
import lombok.Builder.Default;

@Entity
@Table(name = "trips")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

  @Id
  @GeneratedValue(generator = "tripSeq")
  @SequenceGenerator(name = "tripSeq", sequenceName = "trip_id_seq", allocationSize = 1, initialValue = 1)
  private Long id;

  private String name;
  private String description;
  private LocalDate date;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TripStatus status;

  @ManyToOne
  @JoinColumn(name = "created_by_id")
  private User createdBy;

  @ManyToMany
  @JoinTable(name = "trip_participants", joinColumns = @JoinColumn(name = "trip_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
  @Default
  private List<User> participants = new ArrayList<>();
}
