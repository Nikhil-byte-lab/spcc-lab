package programs;
import java.util.*;

public class TargetCode {
	public static void main(String[] args) {
		List<IntermediateInstruction> instructions = new ArrayList<>();
		instructions.add(new IntermediateInstruction("MOV", "10", null, "A"));
        instructions.add(new IntermediateInstruction("MOV", "20", null, "B"));
        instructions.add(new IntermediateInstruction("ADD", "A", "B", "C"));
        instructions.add(new IntermediateInstruction("SUB", "C", "5", "D"));
        instructions.add(new IntermediateInstruction("MUL", "D", "2", "E"));
        instructions.add(new IntermediateInstruction("DIV", "E", "3", "F"));
        
        X86CodeGenerator.generateCode(instructions);
        
	}
}


class IntermediateInstruction{
	String operator; 
	String op1; 
	String op2; 
	String result;
	
	public IntermediateInstruction(String operator, String op1, String op2, String result) {
		this.operator = operator;
		this.op1 = op1;
		this.op2 = op2;
		this.result = result;
	}
}


class X86CodeGenerator{
	public static void generateCode(List<IntermediateInstruction> instructions) {
		for (IntermediateInstruction instruction: instructions) {
			switch (instruction.operator) {
			case "ADD": System.out.println("ADD " + instruction.op1 + ", " + instruction.op2);
			break;
			
			case "SUB": System.out.println("SUB " + instruction.op1 + ", " + instruction.op2);
			break;
			
			case "MUL": System.out.println("IMUL " + instruction.op1 + ", " + instruction.op2);
			break;
			
			case "DIV": System.out.println("IDIV " + instruction.op1 + ", " + instruction.op2);
			break;
			
			case "MOV": System.out.println("MOV " + instruction.result + ", " + instruction.op1);
            break;
            
			default:
            System.out.println("Invalid operation: " + instruction.operator);
			}
		}
	}
}