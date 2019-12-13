voiture.setOnMouseDragged(mouseDragged -> {
	dragged = true;
	
	double posX = (mouseDragged.getX() - X + voiture.getLayoutX());
	double posY = (mouseDragged.getY() - Y + voiture.getLayoutY());

	for (Vehicule parked : cars) {
		if (parked != voiture) { 
			if (parked.getChrDirection() == 'H') {
				if (  ( posX > parked.getLayoutX() && posX < (parked.getLayoutX()+parked.getIntLongeur()*72) ) && ( (posY > parked.getLayoutY()) && (posY < parked.getLayoutY()+72) )   ) {
					System.out.println("crashed");
				} else {
					if (voiture.getChrDirection() == 'H' && (posX >= 45 && posX <= (45+6*72) - voiture.getIntLongeur()*72)) {
						voiture.setLayoutX(posX);
						voiture.setDblX(posX);
					}
					else if (voiture.getChrDirection() == 'V' && (posY >= 70 && posY <= (70+6*72) - voiture.getIntLongeur()*72)) {
						voiture.setLayoutY(posY);
						voiture.setDblY(posY);
					}
				}
			} else if (parked.getChrDirection() == 'V') {
				if (  ( posY > parked.getLayoutY() && posY < (parked.getLayoutY()+parked.getIntLongeur()*72) ) && ( (posX > parked.getLayoutX()) && (posX < parked.getLayoutX()+72) )   ) {
					System.out.println("crashed");
			} else {
					if (voiture.getChrDirection() == 'H' && (posX >= 45 && posX <= (45+6*72) - voiture.getIntLongeur()*72)) {
						voiture.setLayoutX(posX);
						voiture.setDblX(posX);
					}
					else if (voiture.getChrDirection() == 'V' && (posY >= 70 && posY <= (70+6*72) - voiture.getIntLongeur()*72)) {
					voiture.setLayoutY(posY);
						voiture.setDblY(posY);
					}
				} 
			
			}
		}
	}
}