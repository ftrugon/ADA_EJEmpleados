package org.example.repository

import org.example.model.Empleado
import java.io.File
import java.nio.file.Files

class FileRepository(val PathToRead: File) {

    fun readCSV(): MutableList<Empleado>{
        val listToReturn = mutableListOf<Empleado>()

        val br = Files.newBufferedReader(PathToRead.toPath())

        br.readLine()

        br.forEachLine { linea ->

            if (linea.isNotEmpty() && linea.isNotBlank()){
                val splitedLine = linea.split(",")
                listToReturn.add(
                    Empleado(
                        splitedLine[0].toInt(),
                        splitedLine[1],
                        splitedLine[2],
                        splitedLine[3].toFloat()
                    )
                )
            }

        }

        return listToReturn
    }

}