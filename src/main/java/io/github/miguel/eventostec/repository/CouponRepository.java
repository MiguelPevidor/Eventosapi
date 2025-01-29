package io.github.miguel.eventostec.repository;

import io.github.miguel.eventostec.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
}
