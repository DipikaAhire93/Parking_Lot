package org.example.Models;

import java.util.Date;

public class BaseModel {
    private Long id;
    private Date Created_at;
    private Date Modified_at;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated_at() {
        return Created_at;
    }

    public void setCreated_at(Date created_at) {
        Created_at = created_at;
    }

    public Date getModified_at() {
        return Modified_at;
    }

    public void setModified_at(Date modified_at) {
        Modified_at = modified_at;
    }
}
