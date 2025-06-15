package com.sdas204221.AlumniManagementSystem.model;

import jakarta.persistence.*;

@Entity
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Store the raw Base‑64 JSON string directly.
     * PostgreSQL TEXT can hold it, and JPA will map it automatically.
     */
    //@Lob
    @Column(columnDefinition = "TEXT")
    private String data;

    private String filename;

    public FileEntity() {}

    // Convenience constructor (optional)
    public FileEntity(String data, String filename) {
        this.data = data;
        this.filename = filename;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "FileEntity{" +
                "id=" + id +
                ", data=" + (data == null ? "null" : "[base64, " + data.length() + " chars]") +
                ", filename='" + filename + '\'' +
                '}';
    }
}
