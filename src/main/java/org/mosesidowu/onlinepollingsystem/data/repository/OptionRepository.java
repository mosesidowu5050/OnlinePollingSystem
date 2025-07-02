package org.mosesidowu.onlinepollingsystem.data.repository;

import org.mosesidowu.onlinepollingsystem.data.models.Option;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OptionRepository extends JpaRepository<Option,Long> {

}
