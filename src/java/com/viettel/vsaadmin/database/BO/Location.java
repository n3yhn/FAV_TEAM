/*
 * Copyright (C) 2011 Viettel Telecom. All rights reserved.
 * VIETTEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.viettel.vsaadmin.database.BO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author gpdn_trungnq7
 * @version 1.0
 * @since 1.0
 */
@Entity
@Table(name = "LOCATION")
@NamedQueries({
    @NamedQuery(name = "Location.findAll", query = "SELECT l FROM Location l"),
    @NamedQuery(name = "Location.findByLocationId", query = "SELECT l FROM Location l WHERE l.locationId = :locationId"),
    @NamedQuery(name = "Location.findByLocationName", query = "SELECT l FROM Location l WHERE l.locationName = :locationName"),
    @NamedQuery(name = "Location.findByLocationCode", query = "SELECT l FROM Location l WHERE l.locationCode = :locationCode"),
    @NamedQuery(name = "Location.findByDescription", query = "SELECT l FROM Location l WHERE l.description = :description"),
    @NamedQuery(name = "Location.findByLocationType", query = "SELECT l FROM Location l WHERE l.locationType = :locationType")})
public class Location implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LOCATION_ID")
    private Long locationId;
    @Basic(optional = false)
    @Column(name = "LOCATION_NAME")
    private String locationName;
    @Basic(optional = false)
    @Column(name = "LOCATION_CODE")
    private String locationCode;
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @Column(name = "LOCATION_TYPE")
    private short locationType;
    @OneToMany(mappedBy = "location")
    private Collection<Location> locationCollection;
    @JoinColumn(name = "PARENT_ID", referencedColumnName = "LOCATION_ID")
    @ManyToOne
    private Location location;

    public Location() {
    }

    public Location(Long locationId) {
        this.locationId = locationId;
    }

    public Location(Long locationId, String locationName, String locationCode, short locationType) {
        this.locationId = locationId;
        this.locationName = locationName;
        this.locationCode = locationCode;
        this.locationType = locationType;
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public short getLocationType() {
        return locationType;
    }

    public void setLocationType(short locationType) {
        this.locationType = locationType;
    }

    public Collection<Location> getLocationCollection() {
        return locationCollection;
    }

    public void setLocationCollection(Collection<Location> locationCollection) {
        this.locationCollection = locationCollection;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (locationId != null ? locationId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.locationId == null && other.locationId != null) || (this.locationId != null && !this.locationId.equals(other.locationId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.viettel.vsaadmin.database.BO.Location[locationId=" + locationId + "]";
    }

}
