/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author kayejoanneangelikaplaza
 */

public class Employee {
    public String employeeNo;
    public String lastName;
    public String firstName;
    public String birthday;
    public String address;
    public String phoneNumber;
    public String sssNumber;
    public String philhealthNumber;
    public String tinNumber;
    public String pagibigNumber;
    public String status;
    public String position;
    public String immediateSupervisor;
    public String basicSalary;
    public String riceSubsidy;
    public String phoneAllowance;
    public String clothingAllowance;
    public String grossSemiMonthlyRate;
    public String hourlyRate;

    public Employee(String[] fields) {
        employeeNo = fields[0];
        lastName = fields[1];
        firstName = fields[2];
        birthday = fields[3];
        address = fields[4];
        phoneNumber = fields[5];
        sssNumber = fields[6];
        philhealthNumber = fields[7];
        tinNumber = fields[8];
        pagibigNumber = fields[9];
        status = fields[10];
        position = fields[11];
        immediateSupervisor = fields[12];
        basicSalary = fields[13];
        riceSubsidy = fields[14];
        phoneAllowance = fields[15];
        clothingAllowance = fields[16];
        grossSemiMonthlyRate = fields[17];
        hourlyRate = fields[18];
    }

    @Override
    public String toString() {
        return String.format(
            "Employee No: %s\nName: %s %s\nBirthday: %s\nAddress: %s\nPhone Number: %s\nSSS #: %s\nPhilhealth #: %s\nTIN #: %s\nPag-ibig #: %s\nStatus: %s\nPosition: %s\nSupervisor: %s\nBasic Salary: %s\nRice Subsidy: %s\nPhone Allowance: %s\nClothing Allowance: %s\nGross Semi-monthly Rate: %s\nHourly Rate: %s",
            employeeNo, firstName, lastName, birthday, address, phoneNumber,
            sssNumber, philhealthNumber, tinNumber, pagibigNumber, status,
            position, immediateSupervisor, basicSalary, riceSubsidy,
            phoneAllowance, clothingAllowance, grossSemiMonthlyRate, hourlyRate);
    }
}
