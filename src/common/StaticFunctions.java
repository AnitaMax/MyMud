package common;

import common.CommonContent.DIRECTION;

public class StaticFunctions {
	public static String getDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese ="��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
		}
		return chinese;
	}
	public static String getReverseDirection(CommonContent.DIRECTION direction){
		String chinese = "";
		switch(direction){
		case EAST:
			chinese = "��";break;
		case WEST:
			chinese = "��";break;
		case SOUTH:
			chinese = "��";break;
		case NORTH:
			chinese ="��";break;
		case NORTHEAST:
			chinese = "����";break;
		case NORTHWEST:
			chinese = "����";break;
		case SOUTHEAST:
			chinese = "����";break;
		case SOUTHWEST:
			chinese = "����";break;
		case UP:
			chinese = "��";break;
		case DOWN:
			chinese = "��";break;
		}
		return chinese;
	}
	public static CommonContent.DIRECTION getRDirection(CommonContent.DIRECTION d) {
		switch(d){
		case EAST:
			return DIRECTION.WEST;
		case WEST:
			return DIRECTION.EAST;
		case SOUTH:
			return DIRECTION.NORTH;
		case NORTH:
			return DIRECTION.SOUTH;
		case NORTHEAST:
			return DIRECTION.SOUTHWEST;
		case NORTHWEST:
			return DIRECTION.SOUTHEAST;
		case SOUTHEAST:
			return DIRECTION.NORTHEAST;
		case SOUTHWEST:
			return DIRECTION.NORTHEAST;
		case UP:
			return DIRECTION.DOWN;
		case DOWN:
			return DIRECTION.UP;
		}
		return null;
	}
}
