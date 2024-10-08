package org.example

import java.io.File

fun main() {

    val csvPath = File("C:\\Users\\fran\\IdeaProjects\\ADA_EJEmpleados\\src\\main\\resources\\empleados.csv")
    val fileManager = FileRepository(csvPath)

    val XMLPath = File("C:\\Users\\fran\\IdeaProjects\\ADA_EJEmpleados\\src\\main\\resources\\empleados.xml")
    val XMLManager = XMLRepository(XMLPath)

    XMLManager.writeEmployeeList(fileManager.readCSV())

    XMLManager.modifyEmployee(1,5000f)

    XMLManager.readXML().forEach {
        println("ID: ${it.id}, Apellido: ${it.apellido}, Departamento: ${it.departamento}, Salario: ${it.salario}")
    }
}