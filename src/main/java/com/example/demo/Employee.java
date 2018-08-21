package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


    @Entity

    public class Employee {
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private long id;

        @NotNull
        @Size(min = 2)
        private String firstname;


        @NotNull
        @Size(min = 2)
        private String lastname;


        @NotNull
        @Size(min = 2)
        private String email;


        @NotNull
        @Size(min = 5)
        private String phone;

        private String headshot;

        public String getHeadshot() {
            return headshot;
        }

        public void setHeadshot(String headshot) {
            this.headshot = headshot;
        }

        @ManyToOne(fetch = FetchType.EAGER)
        @JoinColumn(name = "department_id")
        private Department department;

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getFirstname() {
            return firstname;
        }

        public void setFirstname(String firstname) {
            this.firstname = firstname;
        }

        public String getLastname() {
            return lastname;
        }

        public void setLastname(String lastname) {
            this.lastname = lastname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public Department getDepartment() {
            return department;
        }

        public void setDepartment(Department department) {
            this.department = department;
        }


    }