package org.example

import org.example.repository.FileRepository
import org.example.repository.XMLRepository
import java.io.File

fun main() {

    val csvPath = File("C:\\Users\\fran\\IdeaProjects\\ADA_EJEmpleados\\src\\main\\resources\\empleados.csv")
    val fileManager = FileRepository(csvPath)

    val XMLPath = File("C:\\Users\\fran\\IdeaProjects\\ADA_EJEmpleados\\src\\main\\resources\\empleados.xml")
    val XMLManager = XMLRepository(XMLPath)

    XMLManager.writeEmployeeList(fileManager.readCSV())

    XMLManager.modifyEmployee(2,5000f)


    XMLManager.showEmplyees()

}