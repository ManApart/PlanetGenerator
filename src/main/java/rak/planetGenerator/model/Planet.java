package rak.planetGenerator.model;

import rak.utility.grid.Grid;

public class Planet {
	private Grid<Region> regionGrid;
	
	
	public Planet(){
		
	}
	
	@Override
	public String toString() {
		return "Planet with " + regionGrid.getAllSquares().size() + " regions";
	}
	
	public void setRegionGrid(Grid<Region> regionGrid){
		this.regionGrid = regionGrid;
	}
	
	public Region getRegion(int x, int y){
		if (regionGrid != null){
			return regionGrid.getItemAt(x, y);
		}
		return null;
	}
	
	public int getSize(){
		if (regionGrid != null){
			return regionGrid.getSizeInOneDimension();
		}
		return 0;
	}

	/**
	 * @param regionY - the y value of the region
	 * @return a float between -1 and 1,  with 1 being the east, 0 being the middle, and -1 being the west
	 */
	public float getLatitude(int regionY){
		return getLongitude(regionY);
	}
	
	/**
	 * @param regionX - the x value of the region
	 * @return a float between -1 and 1,  with 1 being the north pole, 0 being the equator, and -1 being the south pole
	 */
	public float getLongitude(int regionX){
		float center = regionGrid.getSizeInOneDimension()/2;
		float percent = (regionX-center) / center;
		return percent;
	}

}
