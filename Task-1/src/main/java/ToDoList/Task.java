package ToDoList;

public class Task {

    private int Id;    // the ID of task
    private boolean Status;    // true - done, false - not done
    private String Description;    // What should be done

    Task(String description_input, int id_input) {
        Id = id_input;
        Status = false;
        Description = description_input;
    }

    Task(Task temp) {
        Id = temp.Id;
        Description = temp.Description;
        Status = temp.Status;
    }

    // Подготовка информации о задаче для вывода на консоль
    String Out() {
        String Out = "";
        Out = Out + Id + ". ";
        if(this.Status){
            Out += "[X] ";
        }
        else{
            Out += "[ ] ";
        }
        Out += this.Description;
        return Out;
    }

    // Получит id
    int GetId() {
        return Id;
    }

    // Задать описание задачи
    void SetDescription(String input) {
        Description = input;
    }

    // Получить описание задачи
    String GetDescription() {
        return Description;
    }

    // Получить статус задачи
    boolean GetStatus(){
        return Status;
    }

    // Изменение статуса задачи на противоположный
    void ChangeStatus() {
        Status = !Status;
    }

}
