/**
 * Created by Artem on 22.06.16.
 */
public class VisitorApp {
    public static void main(String[] args) {
        /*Element body=new BodyElement();
        Element engine=new EngineElement();
        Visitor hooligan=new HooliganVisistor();

        body.accept(hooligan);
        engine.accept(hooligan);*/
        Element car=new CarElement();
        car.accept(new HooliganVisistor());
        System.out.println();
        car.accept(new MechanicVisitor());
    }
}

interface Visitor{
    void visit(EngineElement engine);
    void visit(BodyElement body);
    void visit(CarElement car);
    void visit(WheelElement wheel);
}

interface Element{
    void accept(Visitor visitor);
}


class BodyElement implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class EngineElement implements Element{

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class CarElement implements Element{
    Element[] elements;

    public CarElement() {
        this.elements = new Element[]{
                new WheelElement("left front"), new WheelElement("right front"),
                new WheelElement("left back"), new WheelElement("right back"),new BodyElement(), new EngineElement()
        };
    }

    @Override
    public void accept(Visitor visitor) {
        for(Element element:elements){
            element.accept(visitor);
        }
        visitor.visit(this);
    }
}

class WheelElement implements Element{
    private String name;

    public WheelElement(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

class HooliganVisistor implements Visitor{

    @Override
    public void visit(EngineElement engine){
        System.out.println("Run engine");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Knock on the body");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Smoke in the car");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("kick "+wheel.getName()+ " wheel");
    }
}

class MechanicVisitor implements Visitor{

    @Override
    public void visit(EngineElement engine) {
        System.out.println("Diagnostic engine");
    }

    @Override
    public void visit(BodyElement body) {
        System.out.println("Dub body");
    }

    @Override
    public void visit(CarElement car) {
        System.out.println("Check car appearance");
    }

    @Override
    public void visit(WheelElement wheel) {
        System.out.println("pump up "+wheel.getName()+" wheel");
    }
}