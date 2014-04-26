package ViewULM;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class FrameParametre extends JFrame{
	ArrayList<PanelParametre> liste_p_para;
	Integer cParametre;
	JPanel jp2;
	PanelOperation panelOperation;
//	ArrayList<PanelParametre> list_para;
	public FrameParametre(PanelOperation po){
		this.panelOperation=po;
		cParametre=0;
		JPanel jp = new JPanel(new GridLayout(1,0));
		jp2 = new JPanel(new GridLayout(0,1));
		liste_p_para=po.liste_p_para;
		for(int i=0;i<liste_p_para.size();i++){
			jp2.add(liste_p_para.get(i));
			cParametre++;
		}
		setLayout(new GridLayout(1,0));
		add(jp);
		add(jp2);
		JButton bAddParametre = new JButton("Add Parametre");
		JButton bDelParametre = new JButton("Del Parametre");
		JButton bValider = new JButton("Valider");
		jp.add(bAddParametre);
		jp.add(bDelParametre);
		jp.add(bValider);
		//initalisier
//		for(int i=0;i<po.liste_p_para.size();i++){
//			liste_p_para.add(new PanelParametre(vc.classeUML.getListeOp().get(cOperation).getListeParam().get(i).getType(),
//					vc.classeUML.getListeOp().get(cOperation).getListeParam().get(i).getNom()
//					));
//			add(liste_p_para.get(cParametre));
//			cParametre++;
//		}
		bAddParametre.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				liste_p_para.add(new PanelParametre());
				jp2.add(liste_p_para.get(cParametre));
				cParametre++;
				panelOperation.liste_p_para=liste_p_para;
				pack();
			}
			
		});
		
		bDelParametre.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (cParametre!=0){
					cParametre--;
					jp2.remove(liste_p_para.get(cParametre));
					liste_p_para.remove(liste_p_para.get(cParametre));
					
				}
				pack();
			}
			
		});
		bValider.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				panelOperation.cParametre=cParametre;
//				for(int i=0;i<cParametre;i++){
//					ParametreUML parametreUML = new ParametreUML(liste_p_para.get(i).tType.getText(),
//							liste_p_para.get(i).tType.getText()
//							);
//					list_para.add(parametreUML);
//					setVisible(false);
//					//System.out.println(list_p.get(0).getNom());
//				}
				setVisible(false);
			}
			
		});
		setVisible(true);
		pack();
	}
}
