package ToDoList;

public class Task {

    private

    int Id;    // the ID of task
    boolean Status;    // true - done, false - not done
    String Description;    // What should be done

    private boolean CheckInput (String input) {
        for(int i = 0; i < input.length(); i++) {
            if(input.charAt(i) == '\n') {
                return false;
            }
        }
        return true;
    }

    // Конструктор по-умолчанию
    Task() {
        Id = 1;
        Status = false;
        Description = "";
    }

    // Конструктор преобразования типов
    Task(String description_input) {
        Id = 1;
        Status = false;
        if(CheckInput(description_input)) {
            Description = description_input;
        }
        else{
            Description = "";
        }
    }

    // Подготовка информации о задаче для вывода на консоль
    String Out() {
        String Out = "";
        Out = Out + Integer.toString(Id) + ") ";
        if(this.Status){
            Out += "[v] ";
        }
        else{
            Out += "[x] ";
        }
        Out += this.Description;
        return Out;
    }

    // Задать описание задачи
    boolean SetDescription(String input) {
        if(CheckInput(input)) {
            this.Description = input;
            return true;
        }
        return false;
    }

    // Задать статус выполнения задачи
    boolean SetStatus(boolean input) {
        this.Status = input;
        return true;
    }

    // Задать ID задачи
    boolean SetId(int id){
        this.Id = id;
        return true;
    }

    // Получить описание задачи
    String GetDescription(){
        return this.Description;
    }

    // Получить статус задачи
    boolean GetStatus(){
        return this.Status;
    }

    // Получить ID задачи
    int GetId(){
        return this.Id;
    }

    // Изменение статуса задачи на противоположный
    void ChangeStatus() {
        if(this.Status == false){
            this.Status = true;
        }
        else{
            this.Status = false;
        }
    }

}
