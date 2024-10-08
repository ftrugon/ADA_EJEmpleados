package org.example

import org.w3c.dom.Element
import org.w3c.dom.Node
import java.io.File
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import kotlin.coroutines.CoroutineContext

class XMLRepository(
    val XMLPath : File
) {

    fun readXML(): MutableList<Empleado>{
        val db = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val docuToRead = db.parse(XMLPath)
        val root = docuToRead.documentElement
        val nodeList = root.getElementsByTagName("empleado")

        val listToReturn = mutableListOf<Empleado>()

        for (i in 0..nodeList.length-1){
            val nodo = nodeList.item(i)

            if(nodo.nodeType == Node.ELEMENT_NODE){
                val nodoElemeto = nodo as Element

                val textId = nodoElemeto.getElementsByTagName("id").item(0).textContent.toInt()
                val textApellido = nodoElemeto.getElementsByTagName("apellido").item(0).textContent
                val textDepart = nodoElemeto.getElementsByTagName("departamento").item(0).textContent
                val textSalario = nodoElemeto.getElementsByTagName("salario").item(0).textContent.toFloat()

                listToReturn.add(Empleado(textId,textApellido,textDepart,textSalario))
            }
        }
        return listToReturn

    }

    fun writeEmployeeList(employeeList: List<Empleado>){

        val db = DocumentBuilderFactory.newInstance().newDocumentBuilder()
        val document = db.domImplementation.createDocument(null,"empleados",null)

        employeeList.forEach{ emple ->

            val empleado = document.createElement("empleado")
            document.documentElement.appendChild(empleado)

            val id = document.createElement("id")
            val apellido = document.createElement("apellido")
            val departamento = document.createElement("departamento")
            val salario = document.createElement("salario")

            val textId = document.createTextNode(emple.id.toString())
            val textApellido = document.createTextNode(emple.apellido)
            val textDepart = document.createTextNode(emple.departamento)
            val textSalario = document.createTextNode(emple.salario.toString())

            id.appendChild(textId)
            apellido.appendChild(textApellido)
            departamento.appendChild(textDepart)
            salario.appendChild(textSalario)

            empleado.appendChild(id)
            empleado.appendChild(apellido)
            empleado.appendChild(departamento)
            empleado.appendChild(salario)

        }

        val source = DOMSource(document)
        val result = StreamResult(XMLPath)
        val transformer = TransformerFactory.newInstance().newTransformer()
        transformer.setOutputProperty(OutputKeys.INDENT,"yes")

        transformer.transform(source,result)
    }

    fun modifyEmployee(id: Int,salario: Float){

        val employees = readXML()

        val employeeToModify = employees.find { it.id == id }

        if (employeeToModify != null){
            employeeToModify.salario = salario
        }

        writeEmployeeList(employees)
    }

}