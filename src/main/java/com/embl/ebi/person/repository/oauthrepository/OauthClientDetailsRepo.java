package com.embl.ebi.person.repository.oauthrepository;

import com.embl.ebi.person.model.oauthmodels.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OauthClientDetailsRepo extends JpaRepository<OauthClientDetails, Long> {
}
