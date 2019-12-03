package com.squidev.mutants_identifier.services;

import java.util.ArrayList;

import com.squidev.mutants_identifier.entities.Dna;
import com.squidev.mutants_identifier.repositories.DnaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * DnaService
 */
@Service
public class DnaService {

    @Autowired
    DnaRepository repository;
    static char[] ALLOWED_CHARS = new char[] {'A','T','C','G'};
    static int CONSECUTIVE_REPETITIONS = 4;

    @Transactional
    public boolean isMutant (String[] dna) {
        
        //Create the Dna entity to storage the dna
        Dna dnaEntity = new Dna();
        dnaEntity.setDnaData(dna);
        
        char[][] matrix = matrixParser(dna);
        ArrayList<String> extractedSequences = new ArrayList<>();
        //Get a list of dna sequences extracted from rows.
        extractedSequences.addAll(getRows(matrix));
        //Get a list of dna sequences extracted from diagonals in a top-to-bot left-to-right fashion.
        extractedSequences.addAll(getDiagonals(matrix));
        //Get a list of dna sequences extracted from columns.
        char[][] rotatedMatrix = rotateMatrixBy90Dregrees(matrix);
        extractedSequences.addAll(getRows(rotatedMatrix));
        //Get a list of dna sequences extracted from diagonals in a bot-to-top left-to-right fashion.
        extractedSequences.addAll(getDiagonals(rotatedMatrix));

        for (char allowedChar : ALLOWED_CHARS) {;
            String mutantSequence = "";
            for (int i = 0; i < CONSECUTIVE_REPETITIONS; i++) {
                mutantSequence += allowedChar;
            }
            for (String sequence : extractedSequences) {
                if (sequence.contains(mutantSequence)) {
                    dnaEntity.setMutant(true);
                    //save(dnaEntity);
                    return true;
                }
            }
        }
        dnaEntity.setMutant(false);
        //repository.save(dnaEntity);
        return false;
    }

    private char[][] matrixParser (String[] dna) {
        char[][] matrix = new char[dna.length][dna.length];
        //Por cada fila
        for (int i = 0; i < dna.length; i++) {
            for (int j = 0; j < dna.length; j++) {
                matrix[i][j] = dna[i].charAt(j);
            }
        }
        return matrix;
    }

    //Dna sequences extraction from rows.
    private ArrayList<String> getRows (char[][] matrix) {
        ArrayList<String> extractedRows = new ArrayList<>();
            for (int row = 0; row < matrix.length; row++) {
            String rowSequence = new String(matrix[row]);
            extractedRows.add(rowSequence);
        }
        return extractedRows;
    }

    //Dna sequences extraction from diagonals.
    private ArrayList<String> getDiagonals (char[][] matrix) {
        ArrayList<String> extractedDiagonals = new ArrayList<>();
        int diagonalOffset = matrix.length-CONSECUTIVE_REPETITIONS;
        //Principal diagonal.
        String principalDiag = "";
        for (int i = 0; i < matrix.length; i++) {
            principalDiag += matrix[i][i];
        }
        extractedDiagonals.add(principalDiag);
        //Secondary diagonals.
        /*Example:
        dna.length = 6
        Upper diag offset 2
        matrix[0][2]+matrix[1][3]+matrix[2][4]+matrix[3][5]
        Upper diag offset 1
        matrix[0][1]+matrix[1][2]+matrix[2][3]+matrix[3][4]+matrix[4][5]
        Diag principal
        matrix[0][0]+matrix[1][1]+matrix[2][2]+matrix[3][3]+matrix[4][4]+matrix[5][5]
        Lower diag offset 1
        matrix[1][0]+matrix[2][1]+matrix[3][2]+matrix[4][3]+matrix[5][4]
        Lower diag offset 2
        matrix[2][0]+matrix[3][1]+matrix[4][2]+matrix[5][3]
        */
        String secondaryUpperDiag = "";
        String secondaryLowerDiag = "";
        for (int offset = 1; offset <= diagonalOffset; offset++) {
            for (int i = 0; i < matrix.length-offset; i++) {
                secondaryUpperDiag += matrix[i][i+offset];
                secondaryLowerDiag += matrix[i+offset][i];
            }
            extractedDiagonals.add(secondaryUpperDiag);
            extractedDiagonals.add(secondaryLowerDiag);
        }
        return extractedDiagonals;
    }
   
    private char[][] rotateMatrixBy90Dregrees (char[][] matrix) {
        char[][] rotatedMatrix = new char[matrix.length][matrix.length];
        //We iterate from outter to inner rings of the matrix.
        int matrixMaxIndex = matrix.length-1;
        for (int ring = 0; ring < matrix.length/2; ring++) {
            for (int i = ring; i <= matrixMaxIndex-ring; i++) {
                //Left side of the ring to bot side.
                rotatedMatrix[matrixMaxIndex-ring][i] = matrix[i][ring];
                //Bot side of the ring to right side.
                rotatedMatrix[matrixMaxIndex-i][matrixMaxIndex-ring] = matrix[matrixMaxIndex-ring][i];
                //Right side of the ring to top side.
                rotatedMatrix[ring][matrixMaxIndex-i] = matrix[matrixMaxIndex-i][matrixMaxIndex-ring];
                //Top side of the ring to left one.
                rotatedMatrix[i][ring] = matrix[ring][matrixMaxIndex-i];
            }
        }
        return rotatedMatrix;
    }
/*     private boolean isMatrixSquare (String[] dna) throws Exception {
        try {
            for (int i = 0; i < dna.length; i++) {
                if (dna[i].length() != dna.length) {
                    throw new Exception();
                }
                
            }    
        } catch (Exception e) {
            //TODO: handle exception
        }
        return true;
    } */


}