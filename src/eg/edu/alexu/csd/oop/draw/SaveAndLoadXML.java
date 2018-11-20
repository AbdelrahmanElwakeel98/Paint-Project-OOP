package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.print.DocFlavor.STRING;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eg.edu.alexu.csd.oop.draw.Shape;

public class SaveAndLoadXML {

	private Shape[] shapes = new Shape[100];
	private int count;
	private String path, path1, cl;
	private Class cls;

	public SaveAndLoadXML(Shape[] sh, int count, String path) {
		this.shapes = sh;
		this.count = count;
		this.path = path;
	}
	
	public Shape[] getShapesSaved () {
		return this.shapes;
	}
	
	public int getCounter() {
		return this.count;
	}
	
	public void loadxml() {
		Arrays.fill(shapes, null);
		count = 0;
		FactoryShapes f = new FactoryShapes();
		Shape shh = null;
		try {

			File fXmlFile = new File(path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			doc.getDocumentElement().normalize();
			
			NodeList nList = doc.getElementsByTagName("shape");
			NodeList nList1 = doc.getElementsByTagName("Counter");
			count = Integer.parseInt(nList1.item(0).getTextContent());

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					String ide = eElement.getAttribute("id");

					if (ide.contains("LineSegment")) {
						shh = f.getShape(1);
					} else if (ide.contains("Circle")) {
						shh = f.getShape(2);
					} else if (ide.contains("Ellipse")) {
						shh = f.getShape(3);
					} else if (ide.contains("Rect")) {
						shh = f.getShape(4);
					} else if (ide.contains("Square")) {
						shh = f.getShape(5);
					} else if (ide.contains("Triangle")) {
						shh = f.getShape(6);
					}

					Point point = new Point(
							(int) Double.parseDouble(eElement.getElementsByTagName("x").item(0).getTextContent()),
							(int) Double.parseDouble(eElement.getElementsByTagName("y").item(0).getTextContent()));
					Map<String, Double> pro = new HashMap<String, Double>();
					pro.put("First", Double.parseDouble(eElement.getElementsByTagName("x2").item(0).getTextContent()));
					pro.put("Second", Double.parseDouble(eElement.getElementsByTagName("y2").item(0).getTextContent()));

					shh.setPosition(point);
					shh.setProperties(pro);
					Color c3 = new Color(
							Integer.parseInt(eElement.getElementsByTagName("c1").item(0).getTextContent()));
					shh.setColor(c3);
					Color c4 = new Color(
							Integer.parseInt(eElement.getElementsByTagName("c2").item(0).getTextContent()));

					shh.setFillColor(c4);
					shapes[temp] = shh;

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void savejson() {
		String FILENAME = path + ".json";

		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			String content = "This is the content to write into file\n";

			fw = new FileWriter(FILENAME);
			bw = new BufferedWriter(fw);
			bw.write("{\"ShapeArray\" :");
			bw.append("[");

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}

	public void savexml() {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.newDocument();
			// add elements to Document
			Element rootElement = doc.createElementNS("", "Paint");
			// append root element to document
			doc.appendChild(rootElement);
			Element node = doc.createElement("Counter");
			node.appendChild(doc.createTextNode(Integer.toString(count)));
			rootElement.appendChild(node);
			// append first child element to root element
			for (int i = 0; i < shapes.length; i++) {

				if (this.shapes[i] == null) {
					break;
				}
				cls = shapes[i].getClass();
				cl = cls.getName();
				Color c3 = this.shapes[i].getColor();
				Color c4 = this.shapes[i].getFillColor();
				if (c3 == null && c4 == null) {

					rootElement.appendChild(getshape(doc, cl, this.shapes[i].getPosition().getX(),
							this.shapes[i].getPosition().getY(), this.shapes[i].getProperties().get("SecondX"),
							this.shapes[i].getProperties().get("SecondY"), Color.BLACK, Color.BLACK));
				} else if (c3 != null) {
					rootElement.appendChild(getshape(doc, cl, this.shapes[i].getPosition().getX(),
							this.shapes[i].getPosition().getY(), this.shapes[i].getProperties().get("SecondX"),
							this.shapes[i].getProperties().get("SecondY"), this.shapes[i].getColor(), Color.BLACK));
				} else if (c4 != null) {
					rootElement.appendChild(getshape(doc, cl, this.shapes[i].getPosition().getX(),
							this.shapes[i].getPosition().getY(), this.shapes[i].getProperties().get("SecondX"),
							this.shapes[i].getProperties().get("SecondY"), Color.black, this.shapes[i].getFillColor()));
				} else {
					rootElement.appendChild(getshape(doc, cl, this.shapes[i].getPosition().getX(),
							this.shapes[i].getPosition().getY(), this.shapes[i].getProperties().get("SecondX"),
							this.shapes[i].getProperties().get("SecondY"), this.shapes[i].getColor(),
							this.shapes[i].getFillColor()));
				}

				// for output to file, console
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer = transformerFactory.newTransformer();
				// for pretty print
				transformer.setOutputProperty(OutputKeys.INDENT, "yes");
				DOMSource source = new DOMSource(doc);

				// write to console or file

				StreamResult file = new StreamResult(new File(path + ".xml"));

				// write data
				transformer.transform(source, file);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static Node getshape(Document doc, String id, Double x1, Double y1, Double x2, Double y2, Color c1,
			Color c2) {
		Element shapee = doc.createElement("shape");

		// set id attribute
		shapee.setAttribute("id", id);

		// create name element
		shapee.appendChild(getEmployeeElements(doc, shapee, "x", Double.toString(x1)));

		// create age element
		shapee.appendChild(getEmployeeElements(doc, shapee, "y", Double.toString(y1)));

		// create role element
		shapee.appendChild(getEmployeeElements(doc, shapee, "x2", Double.toString(x2)));

		// create gender element
		shapee.appendChild(getEmployeeElements(doc, shapee, "y2", Double.toString(y2)));

		// create gender element
		shapee.appendChild(getEmployeeElements(doc, shapee, "c1", Integer.toString(c1.getRGB())));

		// create gender element
		shapee.appendChild(getEmployeeElements(doc, shapee, "c2", Integer.toString(c2.getRGB())));

		return shapee;
	}

	// utility method to create text node
	private static Node getEmployeeElements(Document doc, Element element, String name, String value) {
		Element node = doc.createElement(name);
		node.appendChild(doc.createTextNode(value));
		return node;
	}
}
